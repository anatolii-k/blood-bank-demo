package anatolii.k.bloodbank.blood.domain;

public enum ABONumericFormat {
    I,
    II,
    III,
    IV;

    public ABO toABOFormat(){
       return  switch (this){
           case I -> ABO.O;
           case II -> ABO.A;
           case III -> ABO.B;
           case IV -> ABO.AB;
       };
    }
}
