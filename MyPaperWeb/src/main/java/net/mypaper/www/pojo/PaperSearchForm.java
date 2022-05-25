package net.mypaper.www.pojo;

import java.util.List;

public class PaperSearchForm extends paper{

    private String account;
    private String dir;
    private String token;
    private List<String> citings;
    private List<String> dirs;


    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public List<String> getDirs() {
        return dirs;
    }

    public String getToken() {
        return token;
    }

    public void setDirs(List<String> dirs) {
        this.dirs = dirs;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getCitings() {
        return citings;
    }

    public void setCitings(List<String> citings) {
        this.citings = citings;
    }

    @Override
    public String toString() {
        return "PaperSearchForm{" +
                "account='" + account + '\'' +
                ", dir='" + dir + '\'' +
                ", token='" + token + '\'' +
                ", citings=" + citings +
                ", dirs=" + dirs +
                '}';
    }
}
