package com.jerry.entity;

public class Register {
    private String rusername;
    private String rpassword;
    private String rpwd;
    private String repwd;
    private String remail;
    private String rphone;

    public String getRusername() {
        return rusername;
    }

    public void setRusername(String rusername) {
        this.rusername = rusername;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getRpwd() {
        return rpwd;
    }

    public void setRpwd(String rpwd) {
        this.rpwd = rpwd;
    }

    public String getRepwd() {
        return repwd;
    }

    public void setRepwd(String repwd) {
        this.repwd = repwd;
    }

    public String getRemail() {
        return remail;
    }

    public void setRemail(String remail) {
        this.remail = remail;
    }

    public String getRphone() {
        return rphone;
    }

    public void setRphone(String rphone) {
        this.rphone = rphone;
    }

    @Override
    public String toString() {
        return "Register{" +
                "rusername='" + rusername + '\'' +
                ", rpassword='" + rpassword + '\'' +
                ", rpwd='" + rpwd + '\'' +
                ", repwd='" + repwd + '\'' +
                ", remail='" + remail + '\'' +
                ", rphone='" + rphone + '\'' +
                '}';
    }
}
