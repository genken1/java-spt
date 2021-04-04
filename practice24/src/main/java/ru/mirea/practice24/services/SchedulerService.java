package ru.mirea.practice24.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class SchedulerService {
    private MarketService marketService;
    private ProductService productService;

    // every minute cron = "0 * * * * *"
    @Scheduled(cron = "0 */30 * * * *")
    @ManagedOperation(description = "Scheduler for deleting and writing contents of directory")
    public void doScheduledTask() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        log.info("SCHEDULED task started at {}", formatter.format(date));

        String path = "src/main/resources/tables";
        File dir = ResourceUtils.getFile(path);
        String absPath = dir.getAbsolutePath();
        Arrays.stream(Objects.requireNonNull(dir.listFiles())).forEach(file -> {
            if (file.isFile()) {
                log.info("File " + file.getName() + " was deleted: " + file.delete());
            }
        });

        BufferedWriter mBufWriter = createWriter(absPath, "markets.txt");
        mBufWriter.write("id|name|address\n");
        marketService.getAll()
                .forEach(market -> {
                            try {
                                mBufWriter.write(
                                        String.format(
                                                "%d|%s|%s\n",
                                                market.getId(),
                                                market.getName(),
                                                market.getAddress()
                                        )
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
        mBufWriter.close();

        BufferedWriter pBufWriter = createWriter(absPath, "products.txt");
        pBufWriter.write("id|market_id|name|price\n");
        productService.getAll()
                .forEach(product -> {
                            try {
                                pBufWriter.write(
                                        String.format(
                                                "%d|%d|%s|%s\n",
                                                product.getId(),
                                                product.getMarket().getId(),
                                                product.getName(),
                                                product.getPrice()
                                        )
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
        pBufWriter.close();

        log.info("SCHEDULED task finished.");
    }

    private BufferedWriter createWriter(String dir, String filename) throws IOException {
        return new BufferedWriter(new FileWriter(String.format("%s/%s", dir, filename)));
    }

    @Autowired
    public void setMarketService(MarketService marketService) {
        this.marketService = marketService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
