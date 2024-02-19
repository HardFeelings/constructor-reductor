package ru.vpt.constructorapp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vpt.constructorapp.store.entities.GearboxVersionEntity;

public interface GearboxVersionRepository extends JpaRepository<GearboxVersionEntity,Long> {
}
