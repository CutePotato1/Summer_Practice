package logicPackage;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileInput.BasicInput;
import pointOfInterest.PointOfInterest;
import satellite.Satellite;
import shoot.Shoot;
import visibilityWindow.VisibilityWindow;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<PointOfInterest> points;
    private List<Satellite> satellites;
    private List<Shoot> shoots;
    private List<VisibilityWindow> windows;

    private void fillFromIput() throws ParseException, JsonProcessingException, FileNotFoundException {
        fillSatellites();
        fillWindows();
        fillShoots();
        fillPoints();
    }

    private void fillWindows() throws ParseException, FileNotFoundException {

        BasicInput input = new BasicInput("Moscow.txt");

        for(int i = 0; i < input.getSize(); i = i+4){
            windows.add(new VisibilityWindow(input, i));
        }

        input = new BasicInput("Ekaterinburg.txt");

        for(int i = 0; i < input.getSize(); i = i+4){
            windows.add(new VisibilityWindow(input, i));
        }

        input = new BasicInput("Ulan-Ude.txt");

        for(int i = 0; i < input.getSize(); i = i+4){
            windows.add(new VisibilityWindow(input, i));
        }

    }

    private void fillShoots() throws ParseException, JsonProcessingException, FileNotFoundException {
        BasicInput input = new BasicInput("shoot.json");
        for(int i = 0; i < input.getSize(); i++){

            shoots.add(new Shoot(input, i));
        }
    }

    private void fillSatellites(){}

    private void fillPoints() throws FileNotFoundException {
        BasicInput input = new BasicInput("points.json");
        List<PointOfInterest> points = new ArrayList<>();
        for(int i = 0; i < input.getSize(); i++){

            points.add(new PointOfInterest(input, i));

        }

    }


    public Data() throws ParseException, JsonProcessingException, FileNotFoundException {
        points = new ArrayList<>();
        satellites = new ArrayList<>();
        shoots = new ArrayList<>();
        windows = new ArrayList<>();
        fillFromIput();
    }

    public List<VisibilityWindow> getAllWindowsFor(Satellite satellite){

        List<VisibilityWindow> newList = new ArrayList<>();
        for(int i = 0; i < windows.size(); i++){
            if(windows.get(i).getSatellite().getName().equals(satellite.getName())){
                newList.add(windows.get(i));
            }
        }

        return newList;
    }

    public List<Shoot> getAllShootsFor(Satellite satellite){

        List<Shoot> newList = new ArrayList<>();
        for(int i = 0; i < shoots.size(); i++){

            if(shoots.get(i).getSatellite().getName().equals(satellite.getName())){

                newList.add(shoots.get(i));
            }
        }

        return newList;

    }

}
