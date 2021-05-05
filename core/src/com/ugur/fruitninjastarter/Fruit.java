package com.ugur.fruitninjastarter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Fruit {
    //ekranlara bağlı olarak değişecek değer
    public static float radius = 60f;


    //bu tarz statik tiplerle çalışırken enum yapısını kullanabiliriz.
    public enum Type {
        REGULAR, EXTRA, ENEMY, LIFE
    }
    Type type;
    //ekrana gelecek objelerin pozisyon ve hızlarını alacağız.
    Vector2 pos, velocity;
    //elmanın biri yere düştüğünde o anda düşen elmaların kullanıcının hakkını götürmemesi için yapılacak işlem değişkeni
    public boolean living = true;

    Fruit(Vector2 pos, Vector2 velocity) {
        this.pos = pos;
        this.velocity = velocity;
        type = Type.REGULAR;
    }
    //objeye tıklanılıp tıklanılmadığını tespit edecek metod
    public boolean clicked(Vector2 click) {
        if(pos.dst2(click) <= radius * radius + 1)
            return true;
        return false;
    }
    //güncel pozisyon almak için
    public final Vector2 getPos() {
        return pos;
    }

    //meyvenin ekranın dışına çıkıp çıkmadığı kontrolü
    public boolean outOfScreen() {
        //direkt 0 demememizin sebebi ekranlara bağlı olarak kullanıcı deneyimini artırıp tam deneyim sağlamak
        return (pos.y < -2f * radius);
    }
    //ekrana gelen objenin devamlı hızının ve ona bağlı olarakta pozisyonunun güncellenmesi
    public void update(float deltaTime) {

        //hızın y ve x değerlerini değiştiriyoruz
        velocity.y -= deltaTime * (Gdx.graphics.getHeight() * 0.17f);
        velocity.x -= deltaTime * Math.signum(velocity.x) * 5f;

        pos.mulAdd(velocity, deltaTime);
    }
}
