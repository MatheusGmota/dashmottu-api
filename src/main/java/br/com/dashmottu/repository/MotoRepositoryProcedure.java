package br.com.dashmottu.repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class MotoRepositoryProcedure {

    @PersistenceContext
    private EntityManager entityManager;

    public String buscarMotosPorPatio() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("pkg_moto_ops.proc_motos_por_patio");

        // Parâmetro de saída
        query.registerStoredProcedureParameter("p_json", String.class, ParameterMode.OUT);

        // Executa a procedure
        query.execute();

        // Retorna o JSON
        return (String) query.getOutputParameterValue("p_json");

    }

    public String exportarDataset() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("pkg_export_ops.proc_exportar_dataset");
        query.registerStoredProcedureParameter("p_json", String.class, ParameterMode.OUT);
        query.execute();

        return (String) query.getOutputParameterValue("p_json");

    }
}
