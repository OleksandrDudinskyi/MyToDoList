package com.oleksandr.mytodolist.model;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public enum State {
    DONE("Done"), UNDONE("Undone"), PENDING("Pending"), OVERDUE("Overdue");
    private String mStatus;

    State(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getStatus() {
        return mStatus;
    }
}
