package com.dxc.payroll.initialization;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dxc.payroll.common.Factory;
import com.dxc.payroll.common.FactoryImpl;
import com.dxc.payroll.common.Register;
import com.dxc.payroll.persistence.jpa.repositories.JpaEmployeeRepository;
import com.dxc.payroll.persistence.jpa.repositories.JpaIndexationRepository;
import com.dxc.payroll.persistence.jpa.repositories.JpaJobRepository;
import com.dxc.payroll.persistence.jpa.repositories.JpaPositionPeriodRepository;
import com.dxc.payroll.persistence.jpa.repositories.JpaRoleRepository;
import com.dxc.payroll.persistence.jpa.repositories.JpaTaxRepository;
import com.dxc.payroll.persistence.jpa.repositories.JpaUserRightRepository;
import com.dxc.payroll.persistence.jpa.utils.TransactionHandlerImpl;
import com.dxc.payroll.persistence.repositories.EmployeeRepository;
import com.dxc.payroll.persistence.repositories.IndexationRepository;
import com.dxc.payroll.persistence.repositories.JobRepository;
import com.dxc.payroll.persistence.repositories.PositionPeriodRepository;
import com.dxc.payroll.persistence.repositories.RoleRepository;
import com.dxc.payroll.persistence.repositories.TaxRepository;
import com.dxc.payroll.persistence.repositories.UserRightRepository;
import com.dxc.payroll.persistence.utils.TransactionHandler;
import com.dxc.payroll.service.implementation.EmployeeServiceImpl;
import com.dxc.payroll.service.implementation.IndexationServiceImpl;
import com.dxc.payroll.service.implementation.JobServiceImpl;
import com.dxc.payroll.service.implementation.PaycheckServiceImpl;
import com.dxc.payroll.service.implementation.PositionHistoryServiceImpl;
import com.dxc.payroll.service.implementation.PositionServiceImpl;
import com.dxc.payroll.service.implementation.RightsServiceImpl;
import com.dxc.payroll.service.implementation.RoleServiceImpl;
import com.dxc.payroll.service.implementation.TaxServiceImpl;
import com.dxc.payroll.services.EmployeeService;
import com.dxc.payroll.services.IndexationService;
import com.dxc.payroll.services.JobService;
import com.dxc.payroll.services.PaycheckService;
import com.dxc.payroll.services.PositionHistoryService;
import com.dxc.payroll.services.PositionService;
import com.dxc.payroll.services.RightsService;
import com.dxc.payroll.services.RoleService;
import com.dxc.payroll.services.TaxService;

/**
 * @author lmitov
 *
 *         Initializes and deinitialization of the payroll web application
 */
@SuppressWarnings("nls")
@WebListener
public class StartupListener implements ServletContextListener {

    /**
     * Logger for StartupListener.
     */
    private static final Logger LOGGER = Logger.getLogger(StartupListener.class.getName());

    private EntityManagerFactory entityManagerFactory;

    /**
     * Include everything that has to be loaded up on application startup
     */
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        LOGGER.log(Level.INFO, "Initializing Payroll ServletContext...");

        entityManagerFactory = Persistence.createEntityManagerFactory("payroll");
        final Register<EntityManager> repositoryRegister = registerRepositories();
        final TransactionHandler transactionHandler = new TransactionHandlerImpl(
                entityManagerFactory, repositoryRegister);

        final Register<TransactionHandler> serviceRegister = registerServices();

        final Factory serviceFactory = new FactoryImpl<>(transactionHandler,
                serviceRegister);
        final ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("serviceFactory", serviceFactory);
    }

    /**
     * Register services.
     *
     * @return Register
     */
    private static Register<TransactionHandler> registerServices() {
        final Register<TransactionHandler> serviceRegister = new Register<>();
        serviceRegister.registerService(EmployeeService.class, EmployeeServiceImpl::new);
        serviceRegister.registerService(IndexationService.class, IndexationServiceImpl::new);
        serviceRegister.registerService(JobService.class, JobServiceImpl::new);
        serviceRegister.registerService(PaycheckService.class, PaycheckServiceImpl::new);
        serviceRegister.registerService(PositionHistoryService.class,
                PositionHistoryServiceImpl::new);
        serviceRegister.registerService(PositionService.class, PositionServiceImpl::new);
        serviceRegister.registerService(RightsService.class, RightsServiceImpl::new);
        serviceRegister.registerService(TaxService.class, TaxServiceImpl::new);
        serviceRegister.registerService(RoleService.class, RoleServiceImpl::new);
        return serviceRegister;
    }

    /**
     * Register repositories.
     *
     * @return Register
     */
    private static Register<EntityManager> registerRepositories() {
        final Register<EntityManager> repositoryRegister = new Register<>();
        repositoryRegister.registerService(EmployeeRepository.class, JpaEmployeeRepository::new);
        repositoryRegister.registerService(IndexationRepository.class,
                JpaIndexationRepository::new);
        repositoryRegister.registerService(JobRepository.class, JpaJobRepository::new);
        repositoryRegister.registerService(PositionPeriodRepository.class,
                JpaPositionPeriodRepository::new);
        repositoryRegister.registerService(TaxRepository.class, JpaTaxRepository::new);
        repositoryRegister.registerService(RoleRepository.class, JpaRoleRepository::new);
        repositoryRegister.registerService(UserRightRepository.class, JpaUserRightRepository::new);
        return repositoryRegister;
    }

    /**
     * Close/Destroy everything that has to be released on application shutdown
     */
    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        LOGGER.log(Level.INFO, "Payroll ServletContext releasing...");

        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }

        LOGGER.log(Level.INFO, "Payroll ServletContext released.");
    }
}
