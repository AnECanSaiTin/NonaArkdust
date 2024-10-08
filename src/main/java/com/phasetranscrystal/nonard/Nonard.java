package com.phasetranscrystal.nonard;

import com.phasetranscrystal.nonard.eventdistribute.DataAttachmentRegistry;
import com.phasetranscrystal.nonard.eventdistribute.EventConsumer;
import com.phasetranscrystal.nonard.opesystem.ArkOpeHandler;
import com.phasetranscrystal.nonard.registry.AttributeTypeRegistry;
import com.phasetranscrystal.nonard.testobjs.TestObjects;
import com.phasetranscrystal.nonatomic.GameBusConsumer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@Mod(Nonard.MOD_ID)
public class Nonard {
    public static final String MOD_ID = "nonard";

    public Nonard(IEventBus modEventBus, ModContainer modContainer) {
        ATTACHMENT_REG.register(modEventBus);
        GameBusConsumer.registerHandlerEvents(s -> s.overworld().getData(DATA.get()));

        AttributeTypeRegistry.REGISTER.register(modEventBus);
        DataAttachmentRegistry.REGISTER.register(modEventBus);

        TestObjects.bootstrap(modEventBus);
        EventConsumer.bootstrapConsumer();
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_REG = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<ArkOpeHandler.WorldAttach>> DATA =
            ATTACHMENT_REG.register("operator_infos", () -> AttachmentType.builder(ArkOpeHandler.WorldAttach::new).serialize(ArkOpeHandler.WorldAttach.CODEC).build());
}
