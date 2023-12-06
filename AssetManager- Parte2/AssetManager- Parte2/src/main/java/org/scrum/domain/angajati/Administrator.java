package org.scrum.domain.angajati;

import jakarta.persistence.Entity;

@Entity
public class Administrator extends Responsabil {
    private Integer aniVechime;
    private String abilitatiM;

    public Administrator() {
        super();
        super.setRole(Role.ADMINISTRATOR);
    }

    public Integer getAniVechime(){
        return aniVechime;
    }

    public void setAniVechime(Integer aniVechime){
        this.aniVechime = aniVechime;
    }

    public String getAbilitati(){
        return abilitatiM;
    }

    public void setAbilitati(Integer abilitati){
        this.abilitatiM = abilitatiM;
    }

    public Administrator (Integer memberId, String name,
                          Integer aniVechime, String abilitatiM){
        super(memberId, name, Role.ADMINISTRATOR);
        this.aniVechime = aniVechime;
        this.abilitatiM = abilitatiM;
    }

    public String getAbilitatiM(AbilitatiMType type) {
        if (AbilitatiMType.MANAGERIAL.equals(type))
            return "manageriale: " + abilitatiM;
        if (AbilitatiMType.TECHNICAL.equals(type))
            return "tehnice: " + this.getAbilities();
        return null;
    }

    @Override
    public void setRole(Role role) {
        throw new Error("Proprietatea rol nu poate fi schimbata!");
    }

    public enum AbilitatiMType {MANAGERIAL, TECHNICAL};

}
