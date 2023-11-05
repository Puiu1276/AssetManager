package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;

import java.util.Collection;
public interface IAssetEntityRepository {
    // Obține următorul ID disponibil pentru un nou activ
    public Integer getNextID();

    // Obține un activ pe baza ID-ului său
    public Asset getById(Integer id);

    // Obține un activ pe baza unui exemplar de activ (de exemplu, pentru a căuta după anumite proprietăți)
    public Asset get(Asset sample);

    // Obține toate activele ca o colecție
    public Collection<Asset> toCollection(); // getAll

    // Adaugă un nou activ în repository
    public Asset add(Asset entity);

    // Adaugă mai multe active în repository
    public Collection<Asset> addAll(Collection<Asset> entities);

    // Elimină un activ din repository
    public boolean remove(Asset entity);

    // Elimină mai multe active din repository
    public boolean removeAll(Collection<Asset> entities);

    // Obține numărul de active din repository
    public int size();
}
