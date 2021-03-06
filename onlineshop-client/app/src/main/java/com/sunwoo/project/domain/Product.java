package com.sunwoo.project.domain;

public class Product {

  private int number;//
  private String name;//
  private int price;//
  private String photo;

  public String toCsvString() {
    return String.format("%d,%s,%d,%s",
        this.getNumber(),
        this.getName(),
        this.getPrice(),
        this.getPhoto()
        );
  }

  public static Product valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Product p = new Product();
    p.setNumber(Integer.parseInt(fields[0]));
    p.setName(fields[1]);
    p.setPrice(Integer.parseInt(fields[2]));
    p.setPhoto(fields[3]);

    return p;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + number;
    result = prime * result + price;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (number != other.number)
      return false;
    if (price != other.price)
      return false;
    return true;
  }

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


}
