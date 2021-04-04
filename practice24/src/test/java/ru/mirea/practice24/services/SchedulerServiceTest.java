package ru.mirea.practice24.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SchedulerServiceTest {
    private SchedulerService schedulerService;

    @MockBean
    private MarketService marketService;

    @MockBean
    private ProductService productService;


    @Autowired
    public void setDataScheduler(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Test
    public void checkDataFilesExist() throws IOException {
        schedulerService.doScheduledTask();

        String path = "src/main/resources/tables";
        File dir = ResourceUtils.getFile(path);
        verify(marketService, times(1)).getAll();
        verify(productService, times(1)).getAll();
        assertTrue(dir.exists());
        String[] files = {"markets.txt", "products.txt"};

        assertTrue(Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .map(File::getName)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList(files)));
    }
}
