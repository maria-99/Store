import Stores.Store;
import Stores.StoreBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoreBuilderTest {

    StoreBuilder storeBuilder;

    @BeforeEach
    public void setUp() {
        storeBuilder = new StoreBuilder();
    }

    @Test
    public void shouldSetNameTest() {
        String name = "name";
        storeBuilder.setName(name);
        assertEquals(name, storeBuilder.getName());
    }

    @Test
    public void setNameShouldThrowExceptionTest() {
        String empty = "";
        assertThrows(IllegalArgumentException.class, () -> storeBuilder.setName(empty));
    }

    @Test
    public void shouldSetMaxSize() {
        int maxSize = 5;
        storeBuilder.setMaxSize(maxSize);
        assertEquals(maxSize, storeBuilder.getMaxSize());
    }

    @Test
    public void setMaxSizeShouldThrowExceptionTest() {
        int maxSize = -5;
        assertThrows(IllegalArgumentException.class, () -> storeBuilder.setMaxSize(maxSize));
    }

    @Test
    public void buildWithoutSizeThrowsExceptionTest() {
        String name = "name";
        assertThrows(IllegalStateException.class,
                () -> storeBuilder.setName(name).build(),
                "Build doesnt throw illegalStateException when maxSize = 0");
    }

    @Test
    public void buildWithoutNameThrowsExceptionTest() {
        int maxSize = 5;
        assertThrows(IllegalStateException.class,
                () -> storeBuilder.setMaxSize(maxSize).build(),
                "Build doesnt throw illegalStateException when name is empty");
    }

    @Test
    public void buildSuccessText() {
        int maxSize = 5;
        String name = "name";
        Store actual = storeBuilder.setMaxSize(maxSize).setName(name).build();
        assertEquals(new Store(maxSize, name), actual);
    }
}
