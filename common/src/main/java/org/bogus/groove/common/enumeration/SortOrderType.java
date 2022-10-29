package org.bogus.groove.common.enumeration;

public enum SortOrderType {
    CREATED_AT("createdAt"),
    LIKE_COUNT("likeCount"),
    COMMENT_COUNT("commentCount");

    private String col;

    SortOrderType(String col) {
        this.col = col;
    }

    public String getCol() {
        return col;
    }
}
