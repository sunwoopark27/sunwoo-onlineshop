package com.sunwoo.project.domain;

public class Shipping {

  private int number;
  private String memberId;
  private int orderNumber;
  private int trackingNumber;
  private int status;
  private String manager;

  public String toCsvString() {
    return String.format("%d,%s,%d,%d,%d,%s",
        this.getNumber(),
        this.getMemberId(),
        this.getOrderNumber(),
        this.getTrackingNumber(),
        this.getStatus(),
        this.getManager()
        );
  }

  public static Shipping valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Shipping s = new Shipping();
    s.setNumber(Integer.parseInt(fields[0]));
    s.setMemberId(fields[1]);
    s.setOrderNumber(Integer.parseInt(fields[2]));
    s.setTrackingNumber(Integer.parseInt(fields[3]));
    s.setStatus(Integer.parseInt(fields[4]));
    s.setManager(fields[5]);

    return s;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
    result = prime * result + number;
    result = prime * result + orderNumber;
    result = prime * result + trackingNumber;
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
    Shipping other = (Shipping) obj;
    if (memberId == null) {
      if (other.memberId != null)
        return false;
    } else if (!memberId.equals(other.memberId))
      return false;
    if (number != other.number)
      return false;
    if (orderNumber != other.orderNumber)
      return false;
    if (trackingNumber != other.trackingNumber)
      return false;
    return true;
  }

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getMemberId() {
    return memberId;
  }
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }
  public int getOrderNumber() {
    return orderNumber;
  }
  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }
  public int getTrackingNumber() {
    return trackingNumber;
  }
  public void setTrackingNumber(int trackingNumber) {
    this.trackingNumber = trackingNumber;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public String getManager() {
    return manager;
  }
  public void setManager(String manager) {
    this.manager = manager;
  }

}
