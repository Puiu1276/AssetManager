package org.scrum.domain.angajati;

import jakarta.persistence.*;
import org.scrum.domain.asset.Asset;
import org.scrum.domain.operatiune.Opeariune;

import java.io.Serializable;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)

public class Responsabil implements Comparable<Responsabil>, Serializable {
    @Id
    @GeneratedValue
    private Integer memberID;

    private String memberName;
    private Role role;

    private String userName;
    private String password;


    @ManyToOne
    private Asset asset;
    @OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = false)
    private Opeariune opeatiuneActiv;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getMemberID() {
        return memberID;
    }
    public void setMemberID(Integer memberID) {
        this.memberID = memberID;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String name) {
        this.memberName = memberName;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Responsabil() {
        super();
    }
    public Responsabil(Integer memberID, String name) {
        super();
        this.memberID = memberID;
        this.memberName = name;
    }
    public Responsabil(Integer memberID, String name, Role role) {
        super();
        this.memberID = memberID;
        this.memberName = name;
        this.role = role;
    }


    public Responsabil(Integer memberID, String name, String userName,
                       String password) {
        super();
        this.memberID = memberID;
        this.memberName = name;
        this.userName = userName;
        this.password = password;
    }


    // caz supra-incarcare
    private String abilities;

    public String getAbilities() {
        return abilities;
    }
    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((memberID == null) ? 0 : memberID.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
//		if (getClass() != obj.getClass())
//			return false;
        Responsabil other = (Responsabil) obj;
        if (memberID == null) {
            if (other.memberID != null)
                return false;
        } else if (!memberID.equals(other.memberID))
            return false;
        if (role != other.role)
            return false;
        return true;
    }

    @Override
    public int compareTo(Responsabil other) {
        if (this.equals(other))
            return 0;
        return this.getMemberID().compareTo(other.getMemberID());
    }
    @Override
    public String toString() {
        return "Member [memberID=" + memberID + ", memberName=" + memberName
                + ", userName=" + userName + ", password=" + password + "]";
    }

    public enum Role{
        MANAGER, ADMINISTRATOR;
    }
}
