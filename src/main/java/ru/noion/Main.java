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
        for (var i = 0; i < width; i++) {
            for (var j = 0; j < height; j++) {
                var x = (double) i / width * 2.0 - 1.0;
                var y = (double) j / height * 2.0 - 1.0;
                var pixel = ' ';
                x = x * ratio * pixelAspect;
                x += Math.sin(frame * 0.0025) * 2.0;
                if (x * x + y * y < 0.01) {
                    pixel = '█';
//                    pixel = '@';
                }
                if (pixel == ' ') {
                    var distance = Math.sqrt(x * x + y * y);
                    var index = (int) (distance / 2 * gradient.length);
                    if (index >= gradient.length) {
                        index = gradient.length - 1;
                    }
                    pixel = gradient[gradient.length - index - 1];
                }
                screen[i + j * width] = pixel;
            }
        }
    }
}