package ru.vpt.constructorapp.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vpt.constructorapp.store.entities.ShaftVersionEntity;

public interface ShaftVersionRepository extends JpaRepository<ShaftVersionEntity,Long> {
}
