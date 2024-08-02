package org.springframework.core.convert.converter;

import java.util.Set;

/**
 * @author abstractMoonAstronaut
 * {@code @date} 2024/7/29
 * {@code @msg} 通用的转换器
 */
public interface GenericConverter {
    /**
     * ConvertiblePair 转换组合 由原始转换class和目标转换class组成
     * 一个通用转换器 是否可以被一个需求使用 其"能力"由ConvertiblePair表述
     */
    Set<ConvertiblePair> getConvertibleTypes();
    Object convert(Object source,Class sourceType,Class targetType);


    public static final class ConvertiblePair {
        private final Class<?> sourceType;
        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class){
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);
        }

        @Override
        public int hashCode() {
            //31 作为乘数有助于生成更均匀的哈希分布。其选择基于实用经验和性能考虑
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }


        @Override
        public String toString() {
            return "sourceType -> " + sourceType.getName() + "   " + "targetType -> " + targetType.getName();
        }
    }
}
