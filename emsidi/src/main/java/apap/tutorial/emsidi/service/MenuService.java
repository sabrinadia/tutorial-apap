package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.model.PegawaiModel;

import java.util.List;

public interface MenuService {
    List<MenuModel> getListMenu();
    void addMenu(MenuModel menu);
    MenuModel getMenuByNamaMenu(String nama);
}