package jp.osima.app.wevr

public class SimilarityUtils {
    /**
     * @return Cosine Similarity.
     */
    public static double similarity(
            List vec0,
            List vec1) {

        def xx = 0d
        def xy = 0d
        def yy = 0d

        int len = vec0.size()
        for (int i = 0; i < len; i++) {
            xx += vec0[i] * vec0[i]
            xy += vec0[i] * vec1[i]
            yy += vec1[i] * vec1[i]
        }

        return xy / Math.sqrt(xx) / Math.sqrt(yy)
    }
}
