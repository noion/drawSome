package ru.noion;

public record Vec3(double x, double y, double z) {
    public Vec3(int x, Vec2 uv) {
        this(x, uv.x(), uv.y());
    }

    public Vec3 sub(Vec3 vec3) {
        return new Vec3(x - vec3.x, y - vec3.y, z - vec3.z);
    }

    public Vec3 sub(Vec2 vec2) {
        return new Vec3(x - vec2.x(), y - vec2.y(), z);
    }

    public Vec3 mul(double v) {
        return new Vec3(x * v, y * v, z * v);
    }

    public Vec3 add(Vec3 vec3) {
        return new Vec3(x + vec3.x, y + vec3.y, z + vec3.z);
    }

    public Vec3 mul(Vec3 ro) {
        return new Vec3(x * ro.x, y * ro.y, z * ro.z);
    }

    public Vec3 abs() {
        return new Vec3(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vec3 minus() {
        return new Vec3(-x, -y, -z);
    }
}
