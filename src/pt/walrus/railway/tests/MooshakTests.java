package pt.walrus.railway.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import pt.walrus.Main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MooshakTests {
    private final static String TEST_FILES_DIR_NAME = "src/pt/walrus/railway/tests/mooshak";
    private final static String SERIALIZED_DATA_FILE_NAME = "railway.dat";
    private final static int TEST_FILES_AMOUNT = 15;

    @BeforeAll
    public static void removeSerializedData() throws IOException {
        Files.deleteIfExists(Path.of(SERIALIZED_DATA_FILE_NAME));
    }

    @RepeatedTest(value = TEST_FILES_AMOUNT)
    public void testMooshak(RepetitionInfo info) throws IOException {
        String inputFileName = "test%02d-in.txt".formatted(info.getCurrentRepetition());
        String outputFileName = "test%02d-out.txt".formatted(info.getCurrentRepetition());
        String expected = Files.readString(Path.of(TEST_FILES_DIR_NAME, outputFileName));
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        System.setIn(Files.newInputStream(Path.of(TEST_FILES_DIR_NAME, inputFileName)));
        System.setOut(new PrintStream(outBytes));
        Main.main(new String[]{});
        String actual = outBytes.toString();
        assertEquals(expected, actual);
    }
}
