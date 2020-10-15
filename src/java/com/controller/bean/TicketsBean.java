/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import com.dao.TktTicketsDAO;
import com.model.pojo.GenPersonalEmpresa;
import com.model.pojo.TktEstadosTicket;
import com.model.pojo.TktPrioridadTicket;
import com.model.pojo.TktTickets;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author anthony.calva
 */
@Named(value = "ticketsBean")
@ViewScoped
//@ManagedBean
//@RequestScoped
public class TicketsBean {
    
    private List<GenPersonalEmpresa> personalEmpresa;
    private List<TktPrioridadTicket> prioridadTickets;
    private List<TktEstadosTicket> estadosTickets;
    private List<TktTickets> ticketsList; 
    private List<TktTickets> searchList;
    private List<TktTickets> searchByCodigoTicketList;
    
    TktTickets ticket = new TktTickets();
    TktTickets newTicket = new TktTickets();
    TktTicketsDAO ticketDao = new TktTicketsDAO();

    public List<GenPersonalEmpresa> getPersonalEmpresa() {
        return personalEmpresa;
    }

    public void setPersonalEmpresa(List<GenPersonalEmpresa> personalEmpresa) {
        this.personalEmpresa = personalEmpresa;
    }

    public List<TktPrioridadTicket> getPrioridadTickets() {
        return prioridadTickets;
    }

    public void setPrioridadTickets(List<TktPrioridadTicket> prioridadTickets) {
        this.prioridadTickets = prioridadTickets;
    }

    public List<TktEstadosTicket> getEstadosTickets() {
        return estadosTickets;
    }

    public void setEstadosTickets(List<TktEstadosTicket> estadosTickets) {
        this.estadosTickets = estadosTickets;
    }

    public List<TktTickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<TktTickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public List<TktTickets> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<TktTickets> searchList) {
        this.searchList = searchList;
    }

    public List<TktTickets> getSearchByCodigoTicketList() {
        return searchByCodigoTicketList;
    }

    public void setSearchByCodigoTicketList(List<TktTickets> searchByCodigoTicketList) {
        this.searchByCodigoTicketList = searchByCodigoTicketList;
    }

    public TktTickets getTicket() {
        return ticket;
    }

    public void setTicket(TktTickets ticket) {
        this.ticket = ticket;
    }

    public TktTickets getNewTicket() {
        return newTicket;
    }

    public void setNewTicket(TktTickets newTicket) {
        this.newTicket = newTicket;
    }

    public TktTicketsDAO getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TktTicketsDAO ticketDao) {
        this.ticketDao = ticketDao;
    }
    
    /**
     * Creates a new instance of TicketsBean
     */
    public TicketsBean() {
    }
    
    @PostConstruct
    public void init() {
        GenPersonalEmpresa personalEmpresa;
        personalEmpresa = new GenPersonalEmpresa(1);
        personalEmpresa.setPrimerApellido("Calva");
        personalEmpresa.setPrimerNombre("Anthony");
        
        personalEmpresa = new GenPersonalEmpresa(2);
        personalEmpresa.setPrimerApellido("Pacheco");
        personalEmpresa.setPrimerNombre("Nelson");
        
        TktPrioridadTicket prioridadTicket;
        prioridadTicket = new TktPrioridadTicket(1);
        prioridadTicket.setNombre("LOW");
        prioridadTickets.add(prioridadTicket);
        
        prioridadTicket = new TktPrioridadTicket(2);
        prioridadTicket.setNombre("MEDIUM");
        prioridadTickets.add(prioridadTicket);
        
        prioridadTicket = new TktPrioridadTicket(3);
        prioridadTicket.setNombre("HIGH");
        prioridadTickets.add(prioridadTicket);
        
        TktEstadosTicket estadosTicket;
        estadosTicket = new TktEstadosTicket(1);
        estadosTicket.setNombre("Open");
        estadosTickets.add(estadosTicket);
        
        estadosTicket = new TktEstadosTicket(2);
        estadosTicket.setNombre("Escalated");
        estadosTickets.add(estadosTicket);
        
        estadosTicket = new TktEstadosTicket(3);
        estadosTicket.setNombre("Clased");
        estadosTickets.add(estadosTicket);
    }
    
    public List <TktTickets> getTickets() {
        ticketsList = ticketDao.AllUsers();
        int count = ticketsList.size();
        return ticketsList;
    }
    
    public void addTicket() {
        Integer userId = 0;
        ticketDao.add(newTicket);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Ticket successfully saved.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void changeTicket(TktTickets ticket) {
        this.ticket = ticket;
    }
    
    public void updateTicket(TktTickets ticket) {
        Integer intCodigoTicket = ticket.getCodigoTicket();
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket No. ", intCodigoTicket.toString());
        RequestContext.getCurrentInstance().showMessageInDialog(message1);
        ticketDao.update(ticket);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Ticket updated successfully .");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        ticket = new TktTickets();
    }
    
    public void deleteTicket(TktTickets ticket) {
        ticketDao.delete(ticket);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Ticket deleted successfully");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void searchByCodigoTicket() {
        searchByCodigoTicketList = ticketDao.SearchByCodigoTicket(ticket.getCodigoTicket());
        int count = searchByCodigoTicketList.size();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Ticket Selected:", Integer.toString(count));
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edited Ticket No. ", String.valueOf(((TktTickets) event.getObject()).getCodigoTicket()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        TktTickets editedTicket = (TktTickets) event.getObject();
        ticketDao.update(editedTicket);
    }
    
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        ticketsList.remove((TktTickets) event.getObject());
    }
    
}
