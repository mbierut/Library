package pl.mbierut.enums;

import lombok.Getter;

@Getter
public enum BookStatus {
    IN_STOCK("in stock"), CHECKED_OUT("checked out"), DESTROYED("destroyed"), LOST("lost"), REPLACED("replaced");

    private String status;

    BookStatus(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
