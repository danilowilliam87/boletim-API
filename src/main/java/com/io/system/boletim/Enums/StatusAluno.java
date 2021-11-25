package com.io.system.boletim.Enums;

public enum StatusAluno {
    RECUPERACAO(1, "RECUPERACAO"),
    REPROVADO(2, "REPROVADO"),
    APROVADO(3, "APROVADO");

    private int codigo;
    private String descricao;

    private StatusAluno(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static StatusAluno toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }
        for(StatusAluno sa : values()){
            if(codigo.equals(sa.getCodigo())){
                return sa;
            }
        }
        throw new IllegalArgumentException("Id do Status inválido : " + codigo);
    }
}
