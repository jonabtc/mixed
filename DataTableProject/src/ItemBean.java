import sun.security.validator.ValidatorException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named("itemBean")
@RequestScoped
public class ItemBean implements Serializable {

    final UserPreferences userPreferences = new UserPreferences();

    private HtmlDataTable dataTable;
    private int totalPages;
    private int rowsOnPage;
    private int fullPageRow;
    private Double total;
    private List<Item> itemList = new ArrayList<>();
    private List<Item> comprado = new ArrayList<>();
    private List<String[][]> array = new ArrayList<>();
    private Double totalComprado = 0.0;


    /* Para el ordenamiento y filtrado */
    Long filtrado;
    List<Filtro> filtroSelect;
    boolean descendente = false;
    String filtradoName = "";
    Double filtradoPrecio = 0.0;
    boolean mayorQue = true;

    String error;
    boolean errorBool = false;

    public ItemBean() {
        rowsOnPage = 5;
        fullPageRow = 5;
        total = 0.0;

        itemList = userPreferences.getList();
        iniciarFiltros();
        filtrado = 0L;
    }

    void iniciarFiltros() {
        this.filtroSelect = new ArrayList<>();
        this.filtroSelect.add(new Filtro(1L, "Serial"));
        this.filtroSelect.add(new Filtro(2L, "Product"));
        this.filtroSelect.add(new Filtro(3L, "Unidades en stock"));
        this.filtroSelect.add(new Filtro(4L, "Price"));
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    public int getTotalPages() {
        totalPages = itemList.size() / fullPageRow + 1;
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getRowsOnPage() {
        return rowsOnPage;
    }

    public void setRowsOnPage(int rowsOnPage) {
        this.rowsOnPage = rowsOnPage;
    }

    public int getFullPageRow() {
        return fullPageRow;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void goToFirstPage() {
        rowsOnPage = 5;
        dataTable.setFirst(0);
    }

    public void goToPreviousPage() {
        dataTable.setFirst(dataTable.getFirst() - dataTable.getRows());
        rowsOnPage = 5;
    }

    public void goToNextPage() {
        dataTable.setFirst(dataTable.getFirst() + dataTable.getRows());

        if ((dataTable.getRowCount() - dataTable.getFirst()) < rowsOnPage)
            rowsOnPage = dataTable.getRowCount() % dataTable.getRows();
    }

    public void goToLastPage() {
        int rows = dataTable.getRowCount();
        int displayRows = dataTable.getRows();

        int full = rows / displayRows;
        int mod = rows % displayRows;

        if (mod > 0) {
            dataTable.setFirst(full * displayRows);
            rowsOnPage = mod;
        } else {
            dataTable.setFirst((full - 1) * displayRows);
        }
    }

    public Double getTotal() {
        setTotal();
        return total;
    }

    private void setTotal() {
        total = 0.0;
        for (Item i : itemList)
            total += (i.getPedido() * i.getPrecio());
    }

    public Long getFiltrado() {
        return filtrado;
    }

    public void setFiltrado(Long filtrado) {
        this.filtrado = filtrado;
    }

    public List<Filtro> getFiltroSelect() {
        return filtroSelect;
    }

    public String getFiltradoName() {
        return filtradoName;
    }

    public void setFiltradoName(String filtradoName) {
        this.filtradoName = filtradoName;
    }

    public Double getFiltradoPrecio() {
        return filtradoPrecio;
    }

    public void setFiltradoPrecio(Double filtradoPrecio) {
        this.filtradoPrecio = filtradoPrecio;
    }

    public List<Item> getComprado() {
        return comprado;
    }

    public Double getTotalComprado() {
        return totalComprado;
    }

    public String getError() {
        return error;
    }

    public boolean isErrorBool() {
        return errorBool;
    }

    public void cambiarSort() {
        descendente = !descendente;
        handlerFiltros();
    }

    public void handlerFiltros() {
        rowsOnPage = 5;
        itemList = userPreferences.ordenar(filtrado, this.itemList, descendente);
    }

    public void handlerFiltradoString() {
        rowsOnPage = 5;
        System.out.println("FILTRANDO POR: " + filtradoName);
        itemList = userPreferences.ordenar(filtrado, filtradoName, this.itemList, descendente);
    }


    public void handlerFiltradoPrecio() {
        rowsOnPage = 5;
        itemList = userPreferences.ordenar(filtrado, filtradoPrecio, this.itemList, descendente, mayorQue);
    }

    public String sort() {
        if (descendente) return "↓Za↑";
        return "↑Az↓";
    }

    public String stringButon() {

        if (mayorQue) return ">";

        return "<";
    }

    public void changeButon() {
        mayorQue = !mayorQue;
    }

    public String compra() {


        for (Item item : itemList) {
            if (item.getPedido() > 0) {
                comprado.add(item);
            }
        }

        for (Item i : comprado) {
            totalComprado += (i.getPrecio() * i.getPedido());
        }

        List<Item> aux = userPreferences.listaStock();

        for (Item item : itemList)
            for (Item st : aux)
                if (item.getNombre().equals(st.getNombre()))
                    st.setStock(st.getStock() - item.getPedido());

        userPreferences.setListStore(aux);


        return "totalCompra";
    }

    public void validateComprar(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        List<Item> error = new ArrayList<>();

        for(int i=0; i< itemList.size();i++)
            for(int j=0; j< userPreferences.listaStock().size();j++){
                if(itemList.get(i).getNombre().equals(userPreferences.listaStock().get(j).getNombre())){
                    if((userPreferences.listaStock().get(j).getStock() - itemList.get(i).getPedido()) < 0)
                        error.add(itemList.get(i));
                    continue;
                }

            }
        if (itemList.size() > userPreferences.listaStock().size()) {
            String str = "";
            for(Item i: error)
                str += i.getNombre()+"\n";
            FacesMessage message = new FacesMessage(str);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }


    }
}

@SessionScoped
class UserPreferences implements Serializable {


    private List<Item> list;
    private ItemsStore itemsStore;
    private List<Item> itemsComprados;

    public UserPreferences() {
        itemsStore = ItemsStore.getItemsStore();
        this.list = itemsStore.getItemsStock();
    }

    List<Item> getList() {
        System.out.println("SE ENVIÓ UNA LISTA CON " + list.size());
        return list;
    }

    public void setListStore(List<Item> list) {
        itemsStore.setItemsStock(list);
    }

    public List<Item> getItemsComprados() {
        return itemsComprados;
    }

    public void setItemsComprados(List<Item> itemsComprados) {
        this.itemsComprados = itemsComprados;
    }

    List<Item> ordenar(Long id, List<Item> list, boolean descendente) {
        List<Item> aux = list;
        System.out.println("ORDENAR " + id);
        /*
        (0L, "none")
        (1L, "Serial"));
        (2L, "Product"));
        (3L, "Unidades en stock"));
        (4L, "Price"));*/

        if (id.equals(1L) || id.equals(0L)) {
            if (descendente) {
                aux.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        if (o1.getSerial().compareTo(o2.getSerial()) > 0)
                            return -1;
                        if (o1.getSerial().compareTo(o2.getSerial()) < 0)
                            return 1;
                        return 0;
                    }
                });
                return aux;
            } else {
                aux.sort(new Comparator<Item>() {

                    @Override
                    public int compare(Item o1, Item o2) {

                        return o1.getSerial().compareTo(o2.getSerial());
                    }
                });
                return aux;
            }
        }
        if (id.equals(2L)) {
            if (descendente) {
                System.out.println("Descendente");
                aux.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        if (o1.getNombre().compareTo(o2.getNombre()) > 0)
                            return -1;
                        if (o1.getNombre().compareTo(o2.getNombre()) < 0)
                            return 1;
                        return 0;
                    }
                });
                return aux;
            } else {
                System.out.println("Ascendente");
                aux.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        return (o1.getNombre().compareTo(o2.getNombre()));
                    }
                });
                return aux;
            }
        }
        if (id.equals(3L)) {
            if (descendente) {
                aux.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        if (o1.getStock() < o2.getStock())
                            return 1;
                        if (o1.getStock() > o2.getStock())
                            return -1;
                        return 0;
                    }
                });
                return aux;
            } else {
                aux.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        if (o1.getStock() < o2.getStock())
                            return -1;
                        if (o1.getStock() > o2.getStock())
                            return 1;
                        return 0;
                    }
                });
                return aux;
            }
        }
        if (id.equals(4L)) {
            if (descendente) {
                aux.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        if (o1.getPrecio() < o2.getPrecio())
                            return 1;
                        if (o1.getPrecio() > o2.getPrecio())
                            return -1;
                        return 0;
                    }
                });
                return aux;
            } else {
                aux.sort(new Comparator<Item>() {
                    @Override
                    public int compare(Item o1, Item o2) {
                        if (o1.getPrecio() < o2.getPrecio())
                            return -1;
                        if (o1.getPrecio() > o2.getPrecio())
                            return 1;
                        return 0;
                    }
                });
                return aux;
            }
        }


        return aux;
    }

    List<Item> ordenar(Long id, String cadena, List<Item> list, boolean descendente) {
        List<Item> aux = new ArrayList<>();
        List<Item> aux1 = new ArrayList<>();
        List<Item> enStock = listaStock();

        for (Item item : list)
            if (item.getPedido() > 0)
                for (Item i : enStock)
                    if (item.getNombre().equals(i.getNombre()))
                        i.setPedido(item.getPedido());
        aux = enStock;

        if (cadena.contains("*")) {
            cadena = cadena.replace("*", "[a-zA-z]*");
            Pattern pat = Pattern.compile(cadena.toLowerCase());

            for (Item i : aux) {
                Matcher match = pat.matcher(i.getNombre());
                if (match.find())
                    aux1.add(i);
            }
            System.out.println("Estamos con " + cadena);
            return aux1;
        }
        if (cadena.length() == 0)
            return ordenar(id, aux, descendente);

        for (Item i : aux)
            if (i.getNombre().contains(cadena.toLowerCase()))
                aux1.add(i);
        return aux1;
    }

    List<Item> ordenar(Long id, Double precio, List<Item> list, boolean descendente, boolean mayorQue) {
        List<Item> aux = new ArrayList<>();
        List<Item> aux1 = new ArrayList<>();
        List<Item> enStock = listaStock();
        for (Item i : list) {
            if (i.getPedido() > 0)
                for (Item st : enStock)
                    if (i.getNombre().equals(st.getNombre()))
                        st.setPedido(i.getPedido());
        }
        aux = enStock;
        if (precio == 0.0) {
            return ordenar(id, aux, descendente);
        } else {
            for (Item i : aux) {
                if (mayorQue) {
                    if (i.getPedido() >= precio)
                        aux1.add(i);
                } else {
                    if (i.getPedido() <= precio)
                        aux1.add(i);
                }
            }
        }

        return aux1;
    }

    List<Item> listaStock() {
        List<Item> aux = new ArrayList<>();
        for (Item i : itemsStore.getItemsStock())
            if (i.getStock() > 0)
                aux.add(i);

        return aux;
    }
}