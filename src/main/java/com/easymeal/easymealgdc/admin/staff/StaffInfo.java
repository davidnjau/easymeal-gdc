package com.easymeal.easymealgdc.admin.staff;

import com.easymeal.easymealgdc.admin.positions.Positions;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class StaffInfo {
    @Id
    private String userId;
    private String profileUrl;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Positions position;

    public StaffInfo() {
    }

    public StaffInfo(String userId, String profileUrl, Positions position) {
        this.userId = userId;
        this.profileUrl = profileUrl;
        this.position = position;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }
}
