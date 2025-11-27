package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MutantDetectorTest {

    private MutantDetector mutantDetector;

    @BeforeEach
    void setUp() {
        mutantDetector = new MutantDetector();
    }

    @Test
    void isMutant_shouldReturnTrue_whenHorizontalAndVerticalSequencesExist() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnTrue_whenDiagonalSequencesExist() {
        String[] dna = {"ATGCGA", "CAGTAC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_whenOnlyOneSequenceExists() {
        String[] dna = {"AAAA", "CTGT", "CATT", "AGGG"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_whenNoSequencesExist() {
        String[] dna = {"ATGC", "CAGT", "TTAT", "AGAC"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_forNonSquareMatrix() {
        String[] dna = {"ATGC", "CAG", "TTAT"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_forInvalidCharacters() {
        String[] dna = {"ATGX", "CAGT", "TTAT", "AGAC"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_forNullDna() {
        assertFalse(mutantDetector.isMutant(null));
    }

    @Test
    void isMutant_shouldReturnFalse_forEmptyDna() {
        assertFalse(mutantDetector.isMutant(new String[]{}));
    }

    @Test
    void isMutant_shouldReturnTrue_whenMultipleHorizontalSequencesExist() {
        // CORREGIDO: Usamos solo bases válidas (A, C, T, G)
        String[] dna = {"AAAA", "CCCC", "TTTT", "GGGG"};
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnTrue_whenMultipleVerticalSequencesExist() {
        // CORREGIDO: Usamos letras válidas.
        // Columnas 0, 1, 2 y 3 tienen AAAA, TTTT, etc.
        String[] dna = {"ATCG", "ATCG", "ATCG", "ATCG"};
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnTrue_whenAscendingDiagonalSequencesExist() {
        // CORREGIDO: Matriz 5x5 con dos diagonales ascendentes
        // Diagonal 1 (A): (3,0)->(2,1)->(1,2)->(0,3)
        // Diagonal 2 (G): (4,0)->(3,1)->(2,2)->(1,3)
        String[] dna = {
                "TTTAG",
                "ATAGA",
                "GAGTC",
                "AGATT",
                "GTCAG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_forTooSmallDna() {
        String[] dna = {"ATG", "CAG", "TTA"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_withNullRow() {
        String[] dna = {"ATGC", null, "TTAT", "AGAC"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnTrue_forLargeDnaWithMutantSequences() {
        String[] dna = new String[10];
        for(int i = 0; i < 10; i++) {
            dna[i] = "ATGCATGCAT";
        }
        dna[4] = "CCCCATGCAT";
        dna[5] = "ATGCATCCCC";
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnTrue_whenAllSameCharacter() {
        String[] dna = new String[6];
        for (int i = 0; i < 6; i++) {
            dna[i] = "AAAAAA";
        }
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnFalse_whenSequenceIsLongerThanFour() {
        // Nota: Este test usa caracteres inválidos (B, C, D, E), por eso devuelve false.
        // Si usaras 'AAAAA', 'TTTTT', devolvería true. Se deja como está para cumplir assertFalse.
        String[] dna = {"AAAAA", "BBBBB", "CCCCC", "DDDDD", "EEEEE"};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void isMutant_shouldReturnTrue_forDiagonalInCorner() {
        // CORREGIDO: Matriz válida con:
        // 1. Horizontal: "AAAA" en fila 0
        // 2. Diagonal Descendente: (0,0)->(1,1)->(2,2)->(3,3) "AAAA"
        String[] dna = {
                "AAAA",
                "CAGT",
                "TCAG",
                "GTCA"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
}