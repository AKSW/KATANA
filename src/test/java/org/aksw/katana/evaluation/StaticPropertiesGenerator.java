package org.aksw.katana.evaluation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile({"static"})
public class StaticPropertiesGenerator implements PropertiesGenerator {

    @Value("${info.numberOfShareCandidate}")
    private int numberOfShareCandidate;
    @Value("${info.sizeOfOneSubsetOfShareSome}")
    private int sizeOfOneSubsetOfShareSome;
    @Value("${info.sizeOfOneSubsetOfAllTheSame}")
    private int sizeOfOneSubsetOfAllTheSame;
    @Value("${info.numberOfProperties}")
    private int numberOfProperties;

    @Override
    public List<Integer> getShareCandidateIndices(int i, int numberOfShareSomePO) {
        List<Integer> ret = new ArrayList<>();
        int numberOfShareCandidate = getNumberOfShareCandidate(i, numberOfShareSomePO);

        for (int step = 1, j = 0; j < numberOfShareCandidate; step *= 2, j++)
            if (i + step >= numberOfShareSomePO) break;
            else ret.add(i + step);

        return ret;
    }

    @Override
    public List<Integer> getSharePO_Indices(int numberOfProperties) {
        List<Integer> ret = new ArrayList<>();
        int subSetSize = getSizeOfOneSubsetOfShareSome(numberOfProperties);

        for (int i = 0; i < subSetSize; i++)
            ret.add(i);

        return ret;
    }

    private int getNumberOfShareCandidate(int i, int numberOfShareSomePO) {
        return numberOfShareSomePO - i - 1 > numberOfShareCandidate ? numberOfShareCandidate : numberOfShareSomePO - i - 1;
    }

    private int getSizeOfOneSubsetOfShareSome(int numberOfProperties) {
        return sizeOfOneSubsetOfShareSome;
    }

    @Override
    public int getSizeOfOneSubsetOfAllTheSame() {
        return sizeOfOneSubsetOfAllTheSame;
    }

    @Override
    public int getNumberOfProperties() {
        return numberOfProperties;
    }

}
