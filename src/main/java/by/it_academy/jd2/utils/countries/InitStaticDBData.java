package by.it_academy.jd2.utils.countries;

import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.domain.Diseases;
import by.it_academy.jd2.repository.ICountriesRepository;
import by.it_academy.jd2.repository.IDiseasesRepository;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class InitStaticDBData {

    private final ICountriesRepository repository;
    private final IDiseasesRepository diseasesRepository;
    private final Path filePath;
    private final Path diseasesFilePath;

    public InitStaticDBData(ICountriesRepository repository, IDiseasesRepository diseasesRepository, Path filePath, Path diseasesFilePath) {
        this.repository = repository;
        this.diseasesRepository = diseasesRepository;
        this.filePath = filePath;
        this.diseasesFilePath = diseasesFilePath;
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

    public List<Diseases> readDiseasesDataFromFile(){
        List<Diseases> diseasesList = new ArrayList<>();
        String textLine;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.diseasesFilePath.toString()))){
            textLine = bufferedReader.readLine();
            while(textLine != null){
                String[] split = textLine.split(";");

                Diseases diseases = new Diseases();
                diseases.setCode(split[0]);
                diseases.setName(split[1]);


                diseasesList.add(diseases);

                textLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e){
            System.err.println("Файл не найден.");
        }
        catch (IOException | NullPointerException e){
            e.printStackTrace();
        }

        return diseasesList;
    }

    public void writeCountriesDataToDb(List<Countries> countries){
        for (Countries country : countries) {
            repository.save(country);
        }
    }

    public void writeDiseasesListDataToDb(List<Diseases> diseasesList){
        for (Diseases disease : diseasesList) {
            diseasesRepository.save(disease);
        }
    }

}
