package ru.noion;

public record Vec2 (double x, double y) {
    public Vec2 div(Vec2 vec) {
        return new Vec2(x / vec.x, y / vec.y);
    }

    public Vec2 mul(double v) {
        return new Vec2(x * v, y * v);
    }

    public Vec2 sub(double v) {
        return new Vec2(x - v, y - v);
    }


    public Vec2 mulX(double ratio) {
        return new Vec2(x * ratio, y);
    }
}
