package com.angcyo.oaschool.mode.bean;

/**
 * Created by angcyo on 15-09-12-012.
 */
public class SchoolResult extends BaseResult {

    /**
     * schoolname : 某某市第一中学
     * dataversion : 1
     */
    private String schoolname;
    private String dataversion;

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getDataversion() {
        return dataversion;
    }

    public void setDataversion(String dataversion) {
        this.dataversion = dataversion;
    }
}
