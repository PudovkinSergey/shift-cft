package ru.ftc.android.shifttemple.features.tasks.presentation;

import java.util.List;
import java.util.Set;

import ru.ftc.android.shifttemple.exception.NotAuthorizedException;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.books.domain.model.Success;
import ru.ftc.android.shifttemple.features.tasks.data.BidsLocalDataSource;
import ru.ftc.android.shifttemple.features.tasks.domain.TasksInteractor;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Task;
import ru.ftc.android.shifttemple.network.Carry;

final class TaskPresenter extends MvpPresenter<TaskView> {
    private final TasksInteractor interactor;
    private final BidsLocalDataSource bidsLocalDataSource;

    private String task_id;

    TaskPresenter(TasksInteractor interactor, BidsLocalDataSource bidsLocalDataSource) {
        this.interactor = interactor;
        this.bidsLocalDataSource = bidsLocalDataSource;
    }

    @Override
    protected void onViewReady() {
        loadTask();
        view.changeCloseButtonVisibility(true);
    }

    private void loadTaskBids() {
        interactor.loadTaskBids(task_id, new Carry<List<Bid>>() {

            @Override
            public void onSuccess(List<Bid> result) {

                view.hideProgress();
                Set<String> bidIds = bidsLocalDataSource.getBidIds();

                boolean choosedBidExists = false;

                for (Bid currentBid : result) {
                    for (String currentBidId : bidIds) {
                        if (currentBid.getId().equals(currentBidId)) {
                            choosedBidExists = true;
                            currentBid.setSelected(true);
                        }//
                    }
                }

                //TODO DELETE THIS CODE
//                choosedBidExists = true;
//                Bid firstBid = result.get(0);
//                result.get(0).setSelected(true);
//                firstBid.setEmail("mejdld@gmail.com");
//                firstBid.setPhone("+79231138383");
//                firstBid.setTg_link("telegram.me/oldlentach");
//                firstBid.setVk_link("https://vk.com/oldlentach");
                //TODO DELETE THIS CODE

                view.changeCloseButtonVisibility(!choosedBidExists);

                view.showBidList(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
                if (throwable.getClass() == NotAuthorizedException.class) {
                    view.showLoginForm();
                }
            }
        });
    }


    void onBidSelected(Bid bid) {
        view.showConfirmationDialog(bid);

    }

    private void loadTask() {
        view.showProgress();

        interactor.loadTask(task_id, new Carry<Task>() {
            @Override
            public void onSuccess(Task result) {
                if (view == null) {
                    return;
                }

                view.showTask(result);
                if (result.getTaskIsMine() || true) { // TODO: remove true
                    loadTaskBids();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (view == null) {
                    return;
                }

                view.hideProgress();
                view.showError(throwable.getMessage());
                if (throwable.getClass() == NotAuthorizedException.class) {
                    view.showLoginForm();
                }
            }
        });

    }

    void onRefreshTask() {
        loadTask();

    }

    void onBidLongClicked(Bid bid) {
//        view.showError("May be added to favorite.. May be no;)"); // TODO: favorite
    }

    void setTaskId(final String task_id) {
        this.task_id = task_id;
    }

    public void onCreateBidClicked() {
        view.showInputBidTextDialog();
    }

    public void onBidTextEntered(final String text) {
        view.showProgress();

        final Bid bid = new Bid(task_id, text);

        interactor.createTaskBid(task_id, bid, new Carry<Bid>() {
            @Override
            public void onSuccess(Bid result) {
                view.hideProgress();
                view.showError("Bid added");
                loadTask();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    void onBidChoosed(final Bid bid) {
        view.showProgress();

        interactor.chooseTaskBid(task_id, bid, new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                view.hideProgress();
                view.showError("Bid choosed");
                bidsLocalDataSource.putBidId(bid.getId());
                view.changeCloseButtonVisibility(false);
                loadTaskBids();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    public void onCloseTaskClicked() {
        interactor.finishTask(task_id, new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                if (view == null) {
                    return;
                }
                view.hideProgress();
                view.showError("Task closed");
                view.leaveTaskActivity();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }
}
