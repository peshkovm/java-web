package desktop.cpu;

import desktop.dram.Dram;
import desktop.gpu.Gpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cpu {
    private Gpu gpu;
    private Dram dram;

    Cpu(Gpu gpu) {
        this(gpu, new Dram());
    }

    @Autowired
    Cpu(Gpu gpu, Dram dram) {
        this.gpu = gpu;
        this.dram = dram;
    }
}