package com.android.sanjit.diseaseinformation;

/**
 * Created by Sanjit on 27-Jul-17.
 */

public class Disease {

    private int _id;
    private String _name;
    private String _type;
    private String _symptomps;
    private String _causes;
    private String _first_aid;
    private String _treatment;

    public Disease() {

    }

    public Disease(int id, String name, String _type, String _symptomps) {
        this._id = id;
        this._name = name;
        this._type = _type;
        this._symptomps = _symptomps;
    }

    public Disease(String name, String _type, String _symptomps) {
        this._name = name;
        this._type = _type;
        this._symptomps = _symptomps;
    }

    public Disease(String _name, String _type, String _symptomps, String _causes, String _first_aid, String _treatment) {
        this._name = _name;
        this._type = _type;
        this._symptomps = _symptomps;
        this._causes = _causes;
        this._first_aid = _first_aid;
        this._treatment = _treatment;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String get_type() {
        return this._type;
    }

    public void set_type(String type) {
        this._type = type;
    }

    public String get_symptomps() {
        return this._symptomps;
    }

    public void set_symptomps(String symptomps) {
        this._symptomps = symptomps;
    }

    public String get_causes() {
        return _causes;
    }

    public void set_causes(String _causes) {
        this._causes = _causes;
    }

    public String get_first_aid() {
        return _first_aid;
    }

    public void set_first_aid(String _first_aid) {
        this._first_aid = _first_aid;
    }

    public String get_treatment() {
        return _treatment;
    }

    public void set_treatment(String _treatment) {
        this._treatment = _treatment;
    }
}
