package com.cdh.apitaller.controller;

import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface GenericController<T,S> {
    public ResponseEntity<List<T>> getAll() throws ParseException;
    public ResponseEntity<T> getById(Long id) throws ParseException;
    public ResponseEntity<S> post(S s) throws ParseException;
    public ResponseEntity<S> put(S S) throws ParseException;
    public ResponseEntity<String> delete(Long id) throws ParseException;
}
