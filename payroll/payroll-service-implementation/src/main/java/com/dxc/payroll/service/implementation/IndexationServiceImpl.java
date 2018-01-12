package com.dxc.payroll.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.persistence.domain.Indexation;
import com.dxc.payroll.persistence.repositories.IndexationRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.services.IndexationService;
import com.dxc.payroll.services.dto.IndexationDTO;

/**
 * Implementation of the IndexationService interface.
 */
public class IndexationServiceImpl extends AbstractService implements IndexationService {

    /**
     * Indexation service constructor.
     *
     * @param transactionHandler
     *            must not be null
     */
    public IndexationServiceImpl(final TransactionHandler transactionHandler) {
        super(transactionHandler);
    }

    /**
     * @see com.dxc.payroll.services.IndexationService#getIndexationsBy(java.lang.String)
     */
    @Override
    public List<IndexationDTO> getIndexationsBy(final String jobTitle) {
        return transactionHandler.execute((final Factory factory) -> {
            final IndexationRepository indexationRepository = factory
                    .findService(IndexationRepository.class);
            List<Indexation> indexationsList = indexationRepository.findIndexationBy(jobTitle);

            final List<IndexationDTO> indexationsDTOList = new ArrayList<>();
            for (final Indexation indexation : indexationsList) {
                final IndexationDTO indexationDTO = indexationToIndexationDTO(indexation);
                indexationsDTOList.add(indexationDTO);
            }
            return indexationsDTOList;
        });
    }

    /**
     * @see com.dxc.payroll.services.IndexationService#getAllIndexations()
     */
    @Override
    public List<IndexationDTO> getAllIndexations() {
        return transactionHandler.execute((final Factory factory) -> {
            final IndexationRepository indexationRepository = factory
                    .findService(IndexationRepository.class);
            final List<Indexation> indexationsList = indexationRepository.getIndexations();
            final List<IndexationDTO> indexationsDTOList = new ArrayList<>();
            for (final Indexation indexation : indexationsList) {
                final IndexationDTO indexationDTO = indexationToIndexationDTO(indexation);
                indexationsDTOList.add(indexationDTO);
            }
            return indexationsDTOList;
        });
    }

    /**
     * Converts Indexation object to IndexationDTO object.
     *
     * @param indexation
     * @return IndexationDTO
     */
    private static IndexationDTO indexationToIndexationDTO(final Indexation indexation) {
        return new IndexationDTO(indexation.getPercentage(), indexation.getDateOfIndexation(),
                indexation.getPosition().getJob().getJobTitle(),
                indexation.getPosition().getJob().getJobDegree(),
                indexation.getPosition().getJobLevel());
    }

}
