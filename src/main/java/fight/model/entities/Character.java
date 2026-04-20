package main.java.fight.model.entities;

import java.util.Objects;

public class Character {
    private Integer id;
    private String archetype;
    private String name;
    private Integer attack;
    private Integer defense;
    private String ranking_type;
    private String img_perfil;

    private User user;

    public Character(Integer id, String archetype, String name, Integer attack, Integer defense,
    String ranking, String img, User user) {
        this.id = id;
        this.archetype = archetype;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.ranking_type = ranking;
        this.img_perfil = img;
        this.user = user;
    }

    public Character() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public String getRanking_type() {
        return ranking_type;
    }

    public void setRanking_type(String ranking_type) {
        this.ranking_type = ranking_type;
    }

    public String getImg_perfil() {
        return img_perfil;
    }

    public void setImg_perfil(String img_perfil) {
        this.img_perfil = img_perfil;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", archetype='" + archetype + '\'' +
                ", name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                ", ranking_type='" + ranking_type + '\'' +
                ", img_perfil='" + img_perfil + '\'' +
                ", user=" + user +
                '}';
    }
}
