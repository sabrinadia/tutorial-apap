package apap.tutorial.kebunsafari.service;
import apap.tutorial.kebunsafari.model.KebunSafariModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class KebunSafariInMemoryService implements KebunSafariService{
    private List<KebunSafariModel> listKebunSafari;


    //constructor
    public KebunSafariInMemoryService(){
        listKebunSafari = new ArrayList<>();
    }

    @Override
    public void addKebunSafari(KebunSafariModel kebunSafari){
        listKebunSafari.add(kebunSafari);
    }

    @Override
    public List<KebunSafariModel> getKebunSafariList(){
        return listKebunSafari;
    }

    @Override
    public KebunSafariModel getKebunSafariByIdKebunSafari(String idKebunSafari){

        for (int i = 0; i < listKebunSafari.size(); i++) {
            if(listKebunSafari.get(i).getIdKebunSafari().equals(idKebunSafari)){
               return listKebunSafari.get(i);
            }

        }
        return null;
     }

    @Override
    public KebunSafariModel deleteKebunSafariByIdKebunSafari(KebunSafariModel kebunSafari){
        listKebunSafari.remove(kebunSafari);

        return kebunSafari;

    }

}
