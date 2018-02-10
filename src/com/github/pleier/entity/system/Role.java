package com.github.pleier.entity.system;

import java.io.Serializable;

/**
 * 角色类
 * Created by pleiyang@outlook.com on 2017/3/4.
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 3406338095843144769L;
    private String ROLE_ID;
    private String ROLE_NAME;
    private String RIGHTS;
    private String PARENT_ID;
    private String ADD_QX;
    private String DEL_QX;
    private String EDIT_QX;
    private String CHA_QX;

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME;
    }

    public String getRIGHTS() {
        return RIGHTS;
    }

    public void setRIGHTS(String RIGHTS) {
        this.RIGHTS = RIGHTS;
    }

    public String getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(String PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    public String getADD_QX() {
        return ADD_QX;
    }

    public void setADD_QX(String ADD_QX) {
        this.ADD_QX = ADD_QX;
    }

    public String getDEL_QX() {
        return DEL_QX;
    }

    public void setDEL_QX(String DEL_QX) {
        this.DEL_QX = DEL_QX;
    }

    public String getEDIT_QX() {
        return EDIT_QX;
    }

    public void setEDIT_QX(String EDIT_QX) {
        this.EDIT_QX = EDIT_QX;
    }

    public String getCHA_QX() {
        return CHA_QX;
    }

    public void setCHA_QX(String CHA_QX) {
        this.CHA_QX = CHA_QX;
    }
}
