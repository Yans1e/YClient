package de.yansie.client.mods;

public enum OnOffMods {
    BLOCK_OVERLAY(false),
    RANDOM_PART_SIZES(false),
    Y(false);

    private Boolean value;
    OnOffMods(Boolean b){
        this.value = b;
    }
    public Boolean getValue(){
        return value;
    }
    public void setBoolean(Boolean b){ this.value = b; }
}
