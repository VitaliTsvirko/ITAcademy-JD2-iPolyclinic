package by.it_academy.jd2.utils.countries;

import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.repository.ICountries;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class InitStaticDBData {

    private ICountries repository;
    private Path filePath;

    public InitStaticDBData(ICountries repository, Path filePath) {
        this.repository = repository;
        this.filePath = filePath;
    }


    public List<Countries> readCountriesDataFromFile(){
        List<Countries> countriesList = new ArrayList<>();
        String textLine;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath.toString()))){
            textLine = bufferedReader.readLine();
            while(textLine != null){
                String[] split = textLine.split(";");

                Countries country = new Countries();
                country.setCode(split[0]);
                country.setShotName(split[1]);
                country.setFullName(split[2]);

                countriesList.add(country);

                textLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e){
            System.err.println("Файл не найден.");
        }
        catch (IOException | NullPointerException e){
            e.printStackTrace();
        }

        return countriesList;
    }


    public void writeCountriesDataToDb(List<Countries> countries){
        for (Countries country : countries) {
            repository.save(country);
        }
    }

}
