package org.scrum.domain.angajati;

import jakarta.persistence.Entity;

@Entity
public class Manager extends Responsabil {
    private String domeniu;
    private String skills;

    public Manager() {
        super();
        super.setRole(Responsabil.Role.ADMINISTRATOR);
    }

    public String getDomeniu(){
        return domeniu;
    }

    public void setDomeniu(String Domeniu){
        this.domeniu = domeniu;
    }

    public String getSkills(){
        return skills;
    }

    public void setSkills(String skills){
        this.skills = skills;
    }

    public Manager (Integer memberId, String name,
                          String domeniu, String skills){
        super(memberId, name, Role.MANAGER);
        this.domeniu = domeniu;
        this.skills = skills;
    }

    public String getAbilitatiM(Skills type) {
        if (Skills.COMUNICARE.equals(type))
            return "comunicare: " + skills;
        if (Skills.INDRUMARE.equals(type))
            return "indrumare: " + this.getAbilities();
        return null;
    }

    @Override
    public void setRole(Role role) {
        throw new Error("Proprietatea rol nu poate fi schimbata!");
    }

    public enum Skills {COMUNICARE, INDRUMARE};
}
