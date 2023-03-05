//package assignment.pkg2;
import java.io.Serializable;

/**
* этот класс генеалогического древа действует как реализация класса членов семейства
 */
public class FamilyTree implements Serializable{
    //необходимо для управления версиями сериализованных файлов
    private static final long serialVersionUID = 1;

    /**
     * строит генеалогическое древо, устанавливая корень равным нулю
     */
    public FamilyTree() {
        this.root = null;        
    }
    
    private FamilyMember root;
    
    /**
     * устанавливает корень
     * параметр новый корень
     */
    public void setRoot(FamilyMember newRoot){
        this.root = newRoot;
    }

    public boolean hasRoot(){
        return this.root !=null;
    }
    
    public FamilyMember getRoot(){
        return this.root;
    }
}