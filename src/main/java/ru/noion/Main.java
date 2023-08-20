package ru.noion;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        var height = 59;
        var width = 222;
        var ratio = (double) width / height;
        var pixelAspect = 11.0 / 24.0;
        var screen = new char[width * height + 1];
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), height * width + 1);
        char[] gradient = {' ', '.', '-', ':', '=', '+', '*', '%', '#', '@'};
        for (int frame = 0; frame < 10000; frame++) {
            var start = System.currentTimeMillis();
            drawScene(width, height, ratio, pixelAspect, frame, gradient, screen);
            drawScreen(writer, screen);
            var end = System.currentTimeMillis();
            start = System.currentTimeMillis();
            if (end - start < 10) { // to make it 100 fps
                Thread.sleep(10 - (end - start));
            }
        }
    }

    private static void drawScreen(BufferedWriter writer, char[] screen) throws IOException {
        writer.write("\033[H");
        writer.write(screen, 0, screen.length);
        writer.flush();
    }

    private static void drawScene(int width, int height, double ratio, double pixelAspect, double frame, char[] gradient, char[] screen) {
        var light = VecFunctions.normalize(new Vec3(-0.5, 0.5, -1));
        var sphere = new Vec3(1, 2, 0);
        for (var i = 0; i < width; i++) {
            for (var j = 0; j < height; j++) {
                var uv = new Vec2(i, j).div(new Vec2(width, height)).mul(2.0).sub(1.0);
                uv = uv.mulX(ratio).mulX(pixelAspect);
                var ro = new Vec3(-6, 0, 0);
                var rd = VecFunctions.normalize(new Vec3(2, uv));
                ro = VecFunctions.rotateY(ro, 0.25);
                rd = VecFunctions.rotateY(rd, 0.25);
                ro = VecFunctions.rotateZ(ro, frame * 0.01);
                rd = VecFunctions.rotateZ(rd, frame * 0.01);
                var sphereIntersection = VecFunctions.sphereIntersection(ro.sub(sphere), rd, 1);
                var diff = 1.0;
                if (sphereIntersection.x() > 0) {
                    var itPoint = ro.sub(sphere).add(rd.mul(sphereIntersection.x()));
                    var normal = VecFunctions.normalize(itPoint);
                    diff = Math.max(0.0, VecFunctions.dot(normal, light));
                }
                var color = (int) (diff * (gradient.length));
                color = gradient.length - color - 1;
                color = VecFunctions.clamp(color, 0, gradient.length);
                var pixel = gradient[color];
                screen[j * width + i] = pixel;
            }
        }
    }
}
//}