package com.nick.wood.game_engine.gcs_model;

import com.nick.wood.game_engine.gcs_model.gcs.Component;
import com.nick.wood.game_engine.gcs_model.generated.TransformObject;
import com.nick.wood.game_engine.gcs_model.systems.GcsSystem;
import com.nick.wood.game_engine.gcs_model.systems.TestGcsSystem;
import com.nick.wood.game_engine.event_bus.subscribables.DebugSubscribable;
import com.nick.wood.maths.objects.srt.Transform;
import com.nick.wood.maths.objects.srt.TransformBuilder;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        TransformBuilder transformBuilder = new TransformBuilder();
        Transform transform = transformBuilder.build();

        TransformObject transformObject = new TransformObject("Transform", transform.getPosition(), transform.getScale(), transform.getRotation());

//        GeometryComponent geometryComponent = new GeometryComponent(
//                "Geometry",
//                GeometryType.MODEL,
//                false,
//                transformBuilder.build()
//        );



        ArrayList<GcsSystem<Component>> gcsSystems = new ArrayList<>();
        TestGcsSystem testSystem = new TestGcsSystem();
        gcsSystems.add((GcsSystem) testSystem);

        GameLoop gameLoop = new GameLoop(gcsSystems);

        DebugSubscribable debugSubscribable = new DebugSubscribable();

        gameLoop.getRegistry().createComponent(transformObject);

        //gameLoop.getRegistry().getBus().register(debugSubscribable);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(gameLoop::run);
        executorService.submit(debugSubscribable);
    }
}
