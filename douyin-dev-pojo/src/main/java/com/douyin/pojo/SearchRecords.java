package com.douyin.pojo;

public class SearchRecords {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column search_records.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column search_records.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column search_records.id
     *
     * @return the value of search_records.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column search_records.id
     *
     * @param id the value for search_records.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column search_records.content
     *
     * @return the value of search_records.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column search_records.content
     *
     * @param content the value for search_records.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}