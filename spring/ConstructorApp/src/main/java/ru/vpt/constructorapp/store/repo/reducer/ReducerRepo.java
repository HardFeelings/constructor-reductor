package ru.vpt.constructorapp.store.repo.reducer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReducerRepo extends CrudRepository<ReducerEntity, Long> {
    List<ReducerEntity> findAll();
    Optional<ReducerEntity> findById(Long id);

    @Query(value = "select r.*\n" +
            "from reducer_entity r\n" +
            "where case\n" +
            "          when :diameter_input_shaft is null then r.diameter_input_shaft is null\n" +
            "          else r.diameter_input_shaft = :diameter_input_shaft end\n" +
            "  and case\n" +
            "          when :diameter_output_shaft is null then r.diameter_output_shaft is null\n" +
            "          else r.diameter_output_shaft = :diameter_output_shaft end\n" +
            "  and case when :ratio is null then r.ratio is null else r.ratio = :ratio end\n" +
            "  and case\n" +
            "          when :id_reducer_adapter_type is null then r.id_reducer_adapter_type is null\n" +
            "          else r.id_reducer_adapter_type = :id_reducer_adapter_type end\n" +
            "  and case\n" +
            "          when :id_reducer_input_type is null then r.id_reducer_input_type is null\n" +
            "          else r.id_reducer_input_type = :id_reducer_input_type end\n" +
            "  and case\n" +
            "          when :id_reducer_installation_type is null then r.id_reducer_installation_type is null\n" +
            "          else r.id_reducer_installation_type = :id_reducer_installation_type end\n" +
            "  and case\n" +
            "          when :id_reducer_mounting is null then r.id_reducer_mounting is null\n" +
            "          else r.id_reducer_mounting = :id_reducer_mounting end\n" +
            "  and case\n" +
            "          when :id_reducer_output_shaft_type is null then r.id_reducer_output_shaft_type is null\n" +
            "          else r.id_reducer_output_shaft_type = :id_reducer_output_shaft_type end\n" +
            "  and case when :id_reducer_size is null then r.id_reducer_size is null else r.id_reducer_size = :id_reducer_size end\n" +
            "  and case when :id_reducer_type is null then r.id_reducer_type is null else r.id_reducer_type = :id_reducer_type end;", nativeQuery = true)
    List<ReducerEntity> findByAllParameters(
            @Param("diameter_input_shaft") Integer diameterInputShaft,
            @Param("diameter_output_shaft") Integer diameterOutputShaft,
            @Param("ratio") Double ratio,
            @Param("id_reducer_adapter_type") Long idReducerAdapterType,
            @Param("id_reducer_input_type") Long idReducerInputType,
            @Param("id_reducer_installation_type") Long idReducerInstallationType,
            @Param("id_reducer_mounting") Long idReducerMounting,
            @Param("id_reducer_output_shaft_type") Long idReducerOutputShaftType,
            @Param("id_reducer_size") Long idReducerSize,
            @Param("id_reducer_type") Long idReducerRype);
}
