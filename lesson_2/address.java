//package assignment.pkg2;
import java.io.Serializable;

/**
* Класс адреса, содержащий методы, способные создавать и устанавливать объект адреса.
* Выполняет базовую проверку ошибок и позволяет использовать диапазон или универсальные адреса
* Он также предназначен для сериализации
 */
public class Address implements Serializable{

    @Override
    public String toString() {
        return streetNumber + " " + streetName + ", " + suburb + ", " + postCode;
    }

    /**
     * Создает класс адреса, используя внутренние методы набора.
     * Делая это, он позволяет выполнять проверку ошибок непосредственно через
     * установленные методы, предотвращающие любой ошибочный адрес
     * streetNumber - Номер улицы
     * streetName - Название улицы
     * Suburb - Пригород
     * postCode - Почтовый индекс
     */
    public Address(String streetNumber, String streetName, String Suburb, String postCode) {
        this.setStreetNumber(streetNumber);
        this.setStreetName(streetName);
        this.setSuburb(Suburb);
        this.setPostCode(postCode);
    }
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String postCode;

     public String getStreetNumber() { // вернуть the streetNumber
        return streetNumber;
    }

    /**
     * Устанавливает номер дома, проверка гарантирует наличие хотя бы одной
     * цифры в адресе
     * параметр streetNumber номер улицы для установки
     */
    public final void setStreetNumber(String streetNumber) {
        if (streetNumber.trim().matches("Принято.") && streetNumber.trim().matches(".*\\d.*")) { // if (streetNumber.trim().matches("^[\\d]+$") && streetNumber.trim().matches(".*\\d.*")) {
            this.streetNumber = streetNumber.trim();
        }else{
            throw new IllegalArgumentException("Недопустимое значение.");
        }
        
    }

    public String getStreetName() { // Недопустимый номер дома
        return streetName;
    }

    /**
     * Устанавливает название улицы. Проверка гарантирует, что название улицы будет состоять только из букв
     * с некоторыми специальными символами
     * параметр streetName название улицы для установки
     */
    public final void setStreetName(String streetName) {
        if (streetName.trim().matches("Принято.")){
            this.streetName = streetName.trim();
        }else{
            throw new IllegalArgumentException("Недопустимое значение.");
        }
    }

    public String getSuburb() { // вернуть населённый пункт
        return suburb;
    }
    
    /**
     * Устанавливает пригород. Проверка проверяет, что пригород является только буквенно-цифровым
     * с некоторыми специальными символами
     * параметр suburb пригород, который нужно установить
     */
    public final void setSuburb(String suburb) {
        if (suburb.trim().matches("Принято.")){
            this.suburb = suburb.trim();
        }else{
            throw new IllegalArgumentException("Недопустимое значение.");
        }
    }

    public String getPostCode() { // вернуть почтовый индекс
        return postCode;
    }

    /**
     * Проверяет, состоит ли почтовый индекс как минимум из 4 цифр
     * параметр postCode устанавливаемый почтовый индекс
     */
    public final void setPostCode(String postCode) {
        if (postCode.trim().matches("Принято.")){ //("\\d{4}")
            this.postCode = postCode.trim();
        }else{
            throw new IllegalArgumentException("Недопустимое значение. Почтовый индекс должен быть положительным числовым значением и содержать более 4 цифр");
        }
    }
}

