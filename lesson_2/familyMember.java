//package assignment.pkg2;
import java.io.Serializable;
import java.util.ArrayList;

/**
* Класс членов семьи, который позволяет построить одного члена семьи.
* Затем каждый член имеет своего указанного родственника, размещенного в своем собственном классе.
 */
public class FamilyMember implements Serializable{

    @Override
    public String toString() {
        //отображает красивое строковое представление person. () означает, что у них есть
        //девичья фамилия и для их идентификации используются гендерные символы
        String s = null;
        if (this.gender == Gender.МУЖСКОЙ){
            s = "♂ ";
        }else if (this.gender == Gender.ЖЕНСКИЙ){
            s = "♀ ";
        }
        s += this.getFirstName() + " " + this.getLastName(); 
        if (this.has(Attribute.ДЕВИЧЬЯ_ФАМИЛИЯ)){ 
            s += " (" + this.getMaidenName() + ")";
        }
        return s;
    }

    /**
     * Строит генеалогическое древо, используя методы внутреннего набора. Это позволяет
     * для проверки в центральном месте, делающей любой построенный объект-член семьи действительным
     * параметр firstName - имя
     * параметр lastName - фамилия
     * параметр gender - пол
     * параметр address - Адрес 
     * параметр lifeDescription - Описание
     */
    public FamilyMember(String firstName, String lastName, Gender gender, Address address, String lifeDescription) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.maidenName = "";
        this.setGender(gender);
        this.setAddress(address);
        this.setLifeDescription(lifeDescription);
        
        this.mother = null;
        this.father = null;
        this.spouse = null;
        this.children = new ArrayList<>();
    }
    private String firstName;
    private String lastName;
    private String maidenName;
    private Gender gender;
    private Address address;
    private String lifeDescription;
    // регулярное выражение для соответствия допустимому имени. разрешает любые символы Юникода с некоторыми
    //особые случаи, такие как King Henry Jr. или L'ourve D'Marche
    private final String nameRegex = "^[\\p{L} .'-]+$";
    
    private FamilyMember mother;
    private FamilyMember father;
    private FamilyMember spouse;
    private ArrayList<FamilyMember> children;
    
    /**
     *Типы атрибутов, используемые для проверки наличия у члена семьи какого-либо из этих атрибутов.
     */
    public enum Attribute {
        ОТЕЦ, //FATHER,
        МАТЬ, //MOTHER,
        ДЕТИ, //CHILDREN,
        СУПРУГ, //SPOUSE,
        ДЕВИЧЬЯ_ФАМИЛИЯ, //MAIDENNAME,
        РОДИТЕЛИ, //PARENTS;
    }

    /**
     * Относительные типы, используемые для добавления родственников к члену семьи
     */
    public enum RelativeType {
        ОТЕЦ, //FATHER,
        МАТЬ, //MOTHER,
        РЕБЁНОК, //CHILD,
        СУПРУГ, //SPOUSE;
    } 

    public enum Gender { // Гендерные типы, чтобы обеспечить только два пола
        МУЖСКОЙ, //MALE,
        ЖЕНСКИЙ, //FEMALE;
    }

    public String getFirstName() { // вернуть имя
        return firstName;
    }

    /**
     * параметр firstName — имя, которое необходимо установить
     */
    public final void setFirstName(String firstName) {
        if (firstName.trim().matches(nameRegex)) {
            this.firstName = firstName.trim();
        }else{
            throw new IllegalArgumentException("Недопустимое имя");
        }
        
    }

    public String getLastName() { // вернуть фамилию
        return lastName;
    }

    /**
     * устанавливает фамилию и проверяет соответствие регулярному выражению
     * параметр lastName — фамилия для установки
     */
    public final void setLastName(String lastName) {
        if (lastName.trim().matches(nameRegex)) {
            this.lastName = lastName.trim();
        }else{
            throw new IllegalArgumentException("Недопустимая фамилия");
        }
    }

    public String getMaidenName() { // вернуть девичью фамилию
        return maidenName;
    }

    /**
     * гарантирует, что девичьи фамилии есть только у женщин, и подтверждает это
     * парамер maidenName девичья фамилия для установки
     */
    public void setMaidenName(String maidenName) {
        if (maidenName.trim().matches(nameRegex)) {
            if (this.gender == Gender.ЖЕНСКИЙ){
                this.maidenName = maidenName.trim();
            }else{
                throw new IllegalArgumentException("Девичьи фамилии только для женщин");
            }
            
        }else if (maidenName.isEmpty()){
            this.maidenName = "";
        }else{
            throw new IllegalArgumentException("Недопустимая девичья фамилия");
        }
    }

    public Gender getGender() { // вернуть пол
        return gender;
    }

    public final void setGender(Gender gender) { // параметр пол, который необходимо установить
        this.gender = gender;
    }

    public Address getAddress() { // вернуть адрес
        return address;
    }

    public final void setAddress(Address address) { // параметр адрес для установки
        this.address = address;
    }

    public String getLifeDescription() { // параметр Описание
        return lifeDescription;
    }

    /**
     * @param lifeDescription the lifeDescription to set
     */
    public final void setLifeDescription(String lifeDescription) { // устанавливаем описание
//        if (!lifeDescription.trim().isEmpty()) {
//            this.lifeDescription = lifeDescription;
//        }else{
//            throw new IllegalArgumentException("Description cant be empty");
//        }
// Решено оставить это поле необязательным, так как на самом деле нет необходимости в обязательном описании
        this.lifeDescription = lifeDescription;
        
    }
    
    /**
     * добавляет ребенка к члену семьи. Следовательно, добавление супруга и текущего
     * члена семьи в качестве родителей, если они существуют
     * параметр child дочерний элемент для добавления к набору дочерних элементов
     */
    public void addChild(FamilyMember child) {
        //отец
        if (this.gender == Gender.МУЖСКОЙ) {
            //если у ребенка нет отца, установим его
            if (!child.has(Attribute.ОТЕЦ)) {
                child.setFather(this);
            }
            //если у члена семьи есть супруга, устанавливаем ее матерью
            if (this.has(Attribute.СУПРУГ)) {
                if (!child.has(Attribute.МАТЬ)) {
                    child.setMother(this.getSpouse());
                }
            }
        //мать
        }else if (this.gender == Gender.ЖЕНСКИЙ){
            //если у члена семьи есть супруг, устанавливаем его отцом
            if (!child.has(Attribute.МАТЬ)) {
                child.setMother(this);
            }
            //if the family member has a spouse set it as the father
            if (this.has(Attribute.СУПРУГ)) {
                if (!child.has(Attribute.ОТЕЦ)) {
                    child.setFather(this.getSpouse());
                }
            }
        }
        //убедитесь, что нет повторяющихся дочерних объектов
        if(!this.getChildren().contains(child)){
            this.getChildren().add(child);
        }
    }
    
    public int numChildren(){ // перенастраивает количество детей для этого члена 
        return this.getChildren().size();
    }

    public FamilyMember getMother() { // вернуть мать
        return mother;
    }

    /**
     * устанавливает мать и убеждается, что это самка. он также добавляет текущий m
     * член как ребенок к матери
     * Участник может иметь только одну мать
     * параметр мать, которую нужно установить
     */
    public void setMother(FamilyMember mother) {
        if (!this.has(Attribute.МАТЬ)) {
            if (mother.getGender() == Gender.ЖЕНСКИЙ) {
                if (!mother.getChildren().contains(this)){
                    mother.getChildren().add(this);
                }
                this.mother = mother;

                
            }else{
                throw new IllegalArgumentException("Мать может быть только женского пола");
            }

        }else{
            throw new IllegalArgumentException("Мать уже добавлена");
        }
        
    }

    public FamilyMember getFather() { // вернуть отца
        return father;
    }

    /**
     * устанавливает отца и убеждается, что он мужского пола, также добавляем текущий
     *член в детстве к отцу
     * Участник может иметь только одного отца
     * установим отца
     */
    public void setFather(FamilyMember father) {
        if (!this.has(Attribute.ОТЕЦ)) {
            if (father.getGender() == Gender.МУЖСКОЙ) {
                if (!father.getChildren().contains(this)){
                    father.getChildren().add(this);
                }
                this.father = father;
                
            }else{
                throw new IllegalArgumentException("Отец может быть только мужчиной");
            }
            
        }else{
            throw new IllegalArgumentException("Отец уже добавлен");
        }
        
    }

    public FamilyMember getSpouse() { // вернуть супругу
        return spouse;
    }

    /**
     * устанавливает супруга члена. супруг должен быть противоположного пола и
     * член может иметь только одного супруга
     * параметр супруг для установки
     */
    public void setSpouse(FamilyMember spouse) {
        if (!this.has(Attribute.СУПРУГ)) {
            if(spouse.getGender() != this.getGender()){
                spouse.setChildren(this.getChildren());
                this.spouse = spouse;
                if (!this.getSpouse().has(Attribute.СУПРУГ)) {
                    spouse.setSpouse(this);
                }

            }else{
                throw new IllegalArgumentException("Супруг может быть только противоположного пола");
            }
        }else{
            throw new IllegalArgumentException("Супруг уже существует");
        }
    }

    public ArrayList<FamilyMember> getChildren() { // вернуть детей
        return children;
    }

    public void setChildren(ArrayList<FamilyMember> children) { // параметр дети, который необходимо установить
        this.children = children;
    }
    
    /**
     * проверяет, имеет ли член определенный тип атрибута
     * параметр введите тип атрибута для проверки
     * параметр true, если условия выполнены
     */
    public boolean has(FamilyMember.Attribute type){
        switch(type){
            case ОТЕЦ:
                return this.getFather() != null;
            case ДЕТИ:
                return !this.getChildren().isEmpty();
            case МАТЬ:
                return this.getMother() != null;
            case СУПРУГ:
                return this.getSpouse() != null;
            case ДЕВИЧЬЯ_ФАМИЛИЯ:
                return !this.getMaidenName().isEmpty();
            case РОДИТЕЛИ:
                return this.has(Attribute.ОТЕЦ) || this.has(Attribute.МАТЬ);
        }
        return false;
    }

    /**
     * добавляет родственников на основе указанной переменной типа. В основном удобный метод
     * параметр введите тип добавляемого члена
     * параметр добавляемый член
     */
    public void addRelative(FamilyMember.RelativeType type, FamilyMember member){
        switch(type){
            case ОТЕЦ:
                this.setFather(member);
                return;
            case РЕБЁНОК:
                this.addChild(member);
                return;
            case МАТЬ:
                this.setMother(member);
                return;
            case СУПРУГ:
                this.setSpouse(member);
                return;
        }
    }
}