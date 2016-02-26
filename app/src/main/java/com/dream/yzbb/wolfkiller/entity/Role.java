package com.dream.yzbb.wolfkiller.entity;

/**
 * Created by kevinbest on 16/2/24.
 */
public class Role {
    private String name;
    private String description;
    private int roleID;
    private String introduction;
    // Minimum number
    private int minNumber;
    // Maximum number
    private int maxNumber;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getRoleID() {

        return roleID;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Role) {
            return this.roleID == ((Role) o).getRoleID();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.roleID;
    }

    /**
     * Role object are constructed by parsing definition from XML. Once set, these attributes should not be updated
     **/
    public Role(String name, String description, int roleID, String introduction, int minNumber, int maxNumber) {
        this.name = name;
        this.description = description;
        this.roleID = roleID;
        this.introduction = introduction;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public Role() {
        name="";
        description="";
        introduction="";
        minNumber=0;
        maxNumber=0;
        roleID=-1;
    }

    public Role(String name, int minNumber, int maxNumber, int roleID, String description) {
        this(name, description, roleID, "", 1, 1);
    }

    @Override
    public String toString() {
        return "Role information: roleID - " + roleID + ", name - " + name + ", description - " + description + ",\n introduction - " + introduction + ", min number - " + minNumber + ", max number - " + maxNumber;
    }
}
